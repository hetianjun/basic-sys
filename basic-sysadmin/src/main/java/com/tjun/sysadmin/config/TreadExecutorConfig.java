package com.tjun.sysadmin.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
@EnableAsync
public class TreadExecutorConfig {

	
	/*	使用
	@Autowired
	@Qualifier("taskExecutor")
	private ThreadPoolTaskExecutor taskExecutor;
 
 	taskExecutor.submit(() ->{
 	});
 
	 */
	
    private static final Logger logger = LoggerFactory.getLogger(TreadExecutorConfig.class);
    /**
     *   默认情况下，在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
     *    当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
     *  当队列满了，就继续创建线程，当线程数量大于等于maxPoolSize后，开始使用拒绝策略拒绝
     */

    /**
     * 核心线程数（默认线程数）
     */
    private static final int corePoolSize = 9;
    /**
     * 最大线程数
     */
    private static final int maxPoolSize = 20;
    /**
     * 允许线程空闲时间（单位：默认为秒）
     */
    private static final int keepAliveTime = 60;
    /**
     * 缓冲队列大小
     */
    private static final int queueCapacity = 50;
    /**
     * 线程池名前缀
     */
    private static final String threadNamePrefix = "AsyncThread-";

    @Bean("taskExecutor") // bean的名称，默认为首字母小写的方法名
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setKeepAliveSeconds(keepAliveTime);
        executor.setThreadNamePrefix(threadNamePrefix);

        // 线程池对拒绝任务的处理策略
        // CallerRunsPolicy：由调用线程（提交任务的线程）处理该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        
        //该方法就是这里的关键，用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean，这样这些异步任务的销毁就会先于Redis线程池的销毁。
        executor.setWaitForTasksToCompleteOnShutdown(true);
        //该方法用来设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        executor.setAwaitTerminationSeconds(10);
        
        // 初始化
        executor.initialize();
        return executor;
    }

}
