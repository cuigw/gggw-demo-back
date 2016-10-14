package com.demo.test.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * ClassName:FileExample <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Date:     2016-10-14 上午10:54:34 <br/>
 * @author   cgw
 * @version  
 * @since    JDK 1.6
 * @see 	 
 */
public class StreamExample {
	
	/**
	 * 功能说明: 关于流的一些理解<br>
	 * 
	 * 			关于InputStream 、 OutputStream的一些理解
	 * 
	 * 系统版本: @version 1.0<br>
	 * 开发人员: @author cgw<br>
	 * 开发时间: 2016-10-14 下午2:16:32<br>
	 */
	public static void streamTest() {
		FileOutputStream fos = null;
		FileInputStream fis = null;
		FileOutputStream fos2 = null;
		try {
			fos2 = new FileOutputStream("E:/test2.txt");
			fos = new FileOutputStream("E:/test.txt");
			byte[] bo = "abcdefg".getBytes();
			
			
			/**
			 * 		byte[] b  要写入的byte数组
             *      int off   要写入的byte数组的初始位置
             *      int len   要写入的长度
             *      		如果 off 为负，或 len 为负，或者 off+len 大于数组 b 的长度，则抛出 IndexOutOfBoundsException。
             *      返回
             *      	
             *      
			 */			
			for (int i = 0 ; i < bo.length ; i++) {
				fos.write(bo, i, 1);
			}
			fos.write(bo, 2, 3);
			
			fis = new FileInputStream("E:/test.txt");
			byte[] b = new byte[2];
			byte[] b2 = new byte[20];
			int len = 0 ;
			
			/**
			 * 
			 * 		byte[] b  要写入的byte数组
			 * 		int off   要写入的byte数组的初始位置
			 * 		int len   要写入的长度
			 * 				如果 off 为负，len 为负，或者 len 大于 b.length - off，则抛出 IndexOutOfBoundsException。
			 * 		返回：  len=0  --> 0
			 * 					 --> len
			 * 			    文件末尾   --> -1
			 * 
			 * 
			 * 	InputStream就类比成一个杯子，杯子里的水就像InputStream里的数据，你把杯子里的水拿出来了，杯子的水就没有了，InputStream也是同样的道理。
			 *  在InputStream读取的时候，会有一个pos指针，他指示每次读取之后下一次要读取的起始位置，当读到最后一个字符的时候，pos指针不会重置 
			 * 
			 */	
			int readLen = fis.read(b2, 3 , 8);
			System.out.println("readLen : " + readLen);
			System.out.println(new String(b2));
			
			while (fis.read(b, 0 , 2) > 0) {
				len = len ++;
				System.out.println( new  String(b));
				for (int i = 0; i < b.length; ) {
					System.out.println(i);
					fos2.write(b, i, 1);
					fos2.write("\r\n".getBytes());
					i=  i+1 ;					
				}
			}			
		} catch (IOException e) {			
			e.printStackTrace();
			
		} finally {
			try {
				fos.close();
				fis.close();
				fos2.close();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	
	/**
	 * 功能说明: 过滤流，需要使用已经存在的节点流来构造，提供带缓冲的读写，提高了读写的效率。<br>
	 * 
	 * 			关于BufferedInputStream和BufferedOutputStream的一些理解
	 * 
	 * 系统版本: @version 1.0<br>
	 * 开发人员: @author cgw<br>
	 * 开发时间: 2016-10-14 下午2:42:10<br>
	 */
	public static void bufferStreamTest(){
		FileOutputStream fos = null;
		FileInputStream fis = null;
		FileOutputStream fos2 = null;
		BufferedOutputStream bos = null;
		try {
			
			/**
			 * 
			 * 		flush()和close()的区别：
			 * 			还要使用流对象，还需要写数据，使用flush()，否则使用close():
			 * 					还需要写数据   --> flush();
			 * 					不再写数据	--> close();
			 * 		
			 * 		         另外，使用close()将关闭自己的流对象，同时会关闭与之相关的流对象，如FileOutputStream流。
			 * 
			 */	
			fos2 = new FileOutputStream("E:/bufferTest2.txt");
			fos = new FileOutputStream("E:/bufferTest.txt");
			bos = new BufferedOutputStream(fos);
			byte[] bo = "abcdefg".getBytes();
			bos.write(bo, 0, 3);
			bos.flush();
			bos.write(bo, 3, 3);
			bos.close();
			
			fis = new FileInputStream("E:/bufferTest.txt");
			BufferedInputStream bis = new BufferedInputStream(fis);
			byte[] b = new byte[2];
			byte[] b2 = new byte[20];
			bis.read(b2, 3, 2);
			System.out.println(new String(b2));
			bis.close();
			
		} catch (Exception e) {			
			e.printStackTrace();
			
		} finally {
			try {
				fos.close();
				//fis.close();
				fos2.close();
				bos.close();
			} catch (IOException e) {
				e.printStackTrace();
				
			}
		}
	}
	
	/**
	 * 功能说明: 过滤流，需要使用已经存在的节点流来构造，提供了读写Java中的基本数据类型的功能。<br>
	 * 
	 * 			关于DataInputStream和DataOutputStream 的一些理解
	 * 				
	 * 				主要功能：读写Java中的基本数据类型的功能
	 * 
	 * 
	 * 系统版本: @version 1.0<br>
	 * 开发人员: @author cgw<br>
	 * 开发时间: 2016-10-14 下午2:42:10<br>
	 */
	public static void dataStreamTest(){

	}
	
	public static void createFile() {  
		File f = new File("E:/create.txt");
		try {
			// 当且仅当不存在具有此抽象路径名指定名称的文件时，不可分地创建一个新的空文件。
			f.createNewFile();
			// 返回由此抽象路径名表示的文件或目录的名称。
			System.out.println("该分区大小" + f.getTotalSpace()
					/ (1024 * 1024 * 1024) + "G");
			// 创建此抽象路径名指定的目录，包括所有必需但不存在的父目录。
			f.mkdirs();
			// 删除此抽象路径名表示的文件或目录
			// f.delete();
			// 返回由此抽象路径名表示的文件或目录的名称。
			System.out.println("文件名  " + f.getName());
			// 返回此抽象路径名父目录的路径名字符串；如果此路径名没有指定父目录，则返回 null。
			System.out.println("文件父目录字符串 " + f.getParent());

		} catch (Exception e) {
			e.printStackTrace();
		}
	}  
	
	public static void main(String[] args) throws Exception {  
	       
        //createFile();  
		//streamTest();
		bufferStreamTest();
    }  
}

