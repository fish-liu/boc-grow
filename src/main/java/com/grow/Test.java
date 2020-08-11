package com.grow;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @author liuxw
 * @date 2020/7/16
 * @since 1.0
 */
public class Test {

    public static void main(String [] args){


        final Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run (){
                System.out.println("thread name: " + Thread.currentThread().getName());

                //2PC或是Paxos协议保证一致性

            }
            public void test() throws InterruptedException{
                throw new InterruptedException("test  eeee");
            }
        });


//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("-----interrupt---");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                thread1.interrupt();
//            }
//        });



        thread1.setName("thread1");
        thread1.start();
//        thread.start();



        System.out.println("-----------join----222---");
        try {
            thread1.join();
            System.out.println("-----------join----111---");
        }catch (Exception e){
            System.out.println("-----------join-------");
        }



//        Callable<Integer> cal = new Callable() {
//
//            @Override
//            public Integer call() throws Exception {
//
//                throw new RuntimeException("----run--111-");
//                //return 2;
//            }
//        };
//
//        FutureTask<Integer> futureTask = new FutureTask<Integer>(cal);
//
//        Thread thread2 = new Thread(futureTask);
//        thread2.start();
//
//        try {
//            futureTask.get();
//        }catch (Exception e){
//            System.out.println("-----------futureTask-------");
//        }


    }

}
