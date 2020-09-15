package com.zm.rule;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerRule;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Description 自行实现的一个负载均衡算法规则：同一服务器连续被选中两次就让他重新随机一次，并且要求第三次跟前两次是一定不一样的
 * @Author zm
 * @Date 2020/9/15 0015 9:34
 **/
public class ZmRule2 extends AbstractLoadBalancerRule {

    private int nowIndex = -1;      // 当前的下标
    private int lastIndex = -1;     // 上一次的下标
    private int skipIndex = -1;     // 要跳过的下标（if lastIndex == nowIndex = 要跳过）

    /**
     * 原本的Random Choose方法
     * Randomly choose from all living servers
     */
    public Server choose(ILoadBalancer lb, Object key) {
        if (lb == null) {
            return null;
        }
        Server server = null;

        while (server == null) {
            if (Thread.interrupted()) {
                return null;
            }
            List<Server> upList = lb.getReachableServers();
            List<Server> allList = lb.getAllServers();

            int serverCount = allList.size();
            if (serverCount == 0) {
                /*
                 * No servers. End regardless of pass, because subsequent passes
                 * only get more restrictive.
                 */
                return null;
            }

            int index = chooseRandomInt(serverCount);

            /* ------业务1实现：同一服务器连续被选中两次就让他重新随机一次------ */
            // 判断是否需要跳过
            if(index == skipIndex){
                System.out.println("重新随机一次");
                index = chooseRandomInt(serverCount);
                // 重置需要清除的下标
                skipIndex = -1;
            }

            nowIndex = index;
            if(lastIndex == nowIndex){
                skipIndex = nowIndex;
            }

            // 此次随机操作之后需要将nowIndex的值赋值给lastIndex
            lastIndex = nowIndex;
            /* ------业务1实现：同一服务器连续被选中两次就让他重新随机一次------ */

            server = upList.get(index);

            if (server == null) {
                /*
                 * The only time this should happen is if the server list were
                 * somehow trimmed. This is a transient condition. Retry after
                 * yielding.
                 */
                Thread.yield();
                continue;
            }

            if (server.isAlive()) {
                return (server);
            }

            // Shouldn't actually happen.. but must be transient or a bug.
            server = null;
            Thread.yield();
        }

        return server;

    }

    protected int chooseRandomInt(int serverCount) {
        return ThreadLocalRandom.current().nextInt(serverCount);
    }

    @Override
    public void initWithNiwsConfig(IClientConfig iClientConfig) {

    }

    @Override
    public Server choose(Object key) {
        return choose(getLoadBalancer(), key);
    }
}