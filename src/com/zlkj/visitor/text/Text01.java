package com.zlkj.visitor.text;



import org.junit.Test;


/**
 * 线程 问题
 * @author LYW 
 *  创建时间：2017-8-31 下午5:00:47
 *
 */
public class Text01 implements Runnable {

	@Test
	public void TextSd(){
		run();
	}
	private String name; 

    public Text01(String name) { 
        this.name = name; 
    } 

    public void run() { 
    	for (long k = 0; k < 100000000; k++){ 
        	System.out.println(name + ": " + k); 
        } 
    } 
	public static void main(String[] args) {
		Text01 ds1 = new Text01("阿1"); 
		Text01 ds2 = new Text01("李2"); 
		Text01 ds3 = new Text01("李3"); 
		Text01 ds4 = new Text01("李4"); 
		Text01 ds5 = new Text01("李5"); 

        Thread t1 = new Thread(ds1); 
        Thread t2 = new Thread(ds2); 
        Thread t3 = new Thread(ds3); 
        Thread t4 = new Thread(ds4); 
        Thread t5 = new Thread(ds5); 

        t1.start(); 
        t2.start();
        t3.start();
        t4.start();
        t5.start();
	}
	
	
	
}
