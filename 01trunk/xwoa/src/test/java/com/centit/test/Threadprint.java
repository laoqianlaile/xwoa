package com.centit.test;

    public class Threadprint extends Thread {
        int k = 1; 

        public void run() { 
            int i=k; 
            while(i<50) { 
                System.out.println(Thread.currentThread().getName()+"-----"+i); 
                i+=2; 
            } 
            System.out.println(Thread.currentThread().getName()+" end!"); 
        } 
     /*   public static void main (String[] args) { 
        Threadprint t1=new Threadprint();
        Threadprint t2=new Threadprint(); 
        t1.k = 1;
        t2.k = 2;
        t1.start(); 
        t2.start(); 
        } */
        
        
        public static void main(String[] args)
        {
            int m=0,n=3;
            if(m>0)

                if(n>2)
                    System.out.println("A");    
            else
                System.out.println("B");
            
        }

    }

