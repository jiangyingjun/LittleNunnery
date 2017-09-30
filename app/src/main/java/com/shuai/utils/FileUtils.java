package com.shuai.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

public class FileUtils {

	public FileUtils() {
	}
	
	/**
	* 文件是否存在
	* 
	* @param nativePath
	* @return
	*/
	public static boolean isFileExists(String nativePath) {
		File file = new File(nativePath);
		return file.exists();
	}
	
	/**
	 * 新建文件
	 * 
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 */
	public File creatSDFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
				return myFilePath; 
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return null; 
	}
	
	/**  
     * 将一个InputStream里面的数据写入到SD卡中  
     * @param path  
     * @param fileName  
     * @param input  
     * @return  
     */  
    public File write2SDFromInput(String path,String fileName,InputStream input){  
        File file = null;  
        OutputStream output = null;  
        try {  
        	creatFolder(path);  
            file = creatSDFile(path + fileName);  
            output = new FileOutputStream(file);
			int FILESIZE = 4 * 1024;
			byte[] buffer = new byte[FILESIZE];
  
            /*真机测试，这段可能有问题，请采用下面网友提供的  
                            while((input.read(buffer)) != -1){  
                output.write(buffer);  
            }*/  
  
			/* 网友提供 begin */
			int length;
			while ((length = (input.read(buffer))) > 0) {
				output.write(buffer, 0, length);
			}
			/* 网友提供 end */  
  
            output.flush();  
        }   
        catch (Exception e) {  
            e.printStackTrace();  
        }  
        finally{  
            try {  
                output.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
        return file;  
    }  

	/**
	 * 新建目录
	 * 
	 * @param folderPath
	 *            String 如 c:/fqf
	 * @return boolean
	 */
	public static void creatFolder(String folderPath) {
		try {
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.mkdir();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 新建文件,并写入文本内容
	 *
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @param fileContent
	 *            String 文件内容
	 * @return boolean
	 */
	public void creatFile(String filePathAndName, String fileContent) {

		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			if (!myFilePath.exists()) {
				myFilePath.createNewFile();
			}
			FileWriter resultFile = new FileWriter(myFilePath);
			PrintWriter myFile = new PrintWriter(resultFile);
			myFile.println(fileContent);
			resultFile.close();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件
	 *
	 * @param filePathAndName
	 *            String 文件路径及名称 如c:/fqf.txt
	 * @return boolean
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName.toString();
			File myDelFile = new File(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件夹
	 *
	 * @param folderPath
	 *            String 文件夹路径及名称 如c:/fqf
	 * @return boolean
	 */
	public static void delFolder(String folderPath) {
		try {
			delAllFile(folderPath); // 删除完里面所有内容
			String filePath = folderPath;
			filePath = filePath.toString();
			File myFilePath = new File(filePath);
			myFilePath.delete(); // 删除空文件夹

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 删除文件夹里面的所有文件
	 * 
	 * @param path
	 *            String 文件夹路径 如 c:/fqf
	 */
	public static void delAllFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			return;
		}
		if (!file.isDirectory()) {
			return;
		}
		String[] tempList = file.list();
		File temp;
		for (String aTempList : tempList) {
			if (path.endsWith(File.separator)) {
				temp = new File(path + aTempList);
			} else {
				temp = new File(path + File.separator + aTempList);
			}
			if (temp.isFile()) {
				temp.delete();
			}
			if (temp.isDirectory()) {
				delAllFile(path + "/ " + aTempList);// 先删除文件夹里面的文件
				delFolder(path + "/ " + aTempList);// 再删除空文件夹
			}
		}
	}

	/**
	 * 复制单个文件
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf.txt
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf.txt
	 * @return boolean
	 */
	public void copyFile(String oldPath, String newPath) {
		try {
			int bytesum = 0;
			int byteread;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
				fs.flush();
				fs.close();
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 复制整个文件夹内容
	 * 
	 * @param oldPath
	 *            String 原文件路径 如：c:/fqf
	 * @param newPath
	 *            String 复制后路径 如：f:/fqf/ff
	 * @return boolean
	 */
	public static void copyFolder(String oldPath, String newPath) {

		try {
			String lastName = oldPath.substring(oldPath.lastIndexOf("/") + 1);
			(new File(newPath + "/" + lastName)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
			File a = new File(oldPath);
			String[] file = a.list();
			File temp = null;
			for (String aFile : file) {
				if (oldPath.endsWith(File.separator)) {
					temp = new File(oldPath + aFile);
				} else {
					temp = new File(oldPath + File.separator + aFile);
				}

				if (temp.isFile()) {
					FileInputStream input = new FileInputStream(temp);

					FileOutputStream output = new FileOutputStream(newPath
							+ "/" + lastName + "/ "
							+ (temp.getName()).toString());
					byte[] b = new byte[1024 * 5];
					int len;
					while ((len = input.read(b)) != -1) {
						output.write(b, 0, len);
					}
					output.flush();
					output.close();
					input.close();
				}
				if (temp.isDirectory()) {// 如果是子文件夹
					copyFolder(oldPath + "/ " + aFile, newPath + "/ "
							+ aFile);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * 移动文件到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public void moveFile(String oldPath, String newPath) {
		copyFile(oldPath, newPath);
		delFile(oldPath);

	}

	/**
	 * 移动目录到指定目录
	 * 
	 * @param oldPath
	 *            String 如：c:/fqf.txt
	 * @param newPath
	 *            String 如：d:/fqf.txt
	 */
	public static void moveFolder(String oldPath, String newPath) {
		copyFolder(oldPath, newPath);
		delFolder(oldPath);

	}

	public static void main(String[] args) {
		moveFolder("d:/yuanma", "d:/jar");
	}

	/**
	 * 把字节数组保存为一个文件
	 * @param b
	 * @return
	 */
	public static File getFileFromBytes(byte[] b, String fileName) {
		File ret = null;
		BufferedOutputStream stream = null;
		FileOutputStream fstream=null;
		try {
			String path = getDirectory(Config.Companion.getDIRCETOYR());
			ret = new File(path + fileName);
			fstream = new FileOutputStream(ret);
			stream = new BufferedOutputStream(fstream);
			stream.write(b);
		} catch (Exception e) {
			// LogUtil.e("helper:get file from byte process error!");
			e.printStackTrace();
		} finally {
			if (stream != null) {
				try {
					stream.close();
				} catch (IOException e) {
					// LogUtil.e("helper:get file from byte process error!");
					e.printStackTrace();
				}
			}

			if (fstream!=null){
				try {
					fstream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

	/**
	 * 保存文件
	 * 
	 * @param bm
	 * @param fileName
	 * @throws IOException
	 */
	public String saveFile(Bitmap bm, String fileName) throws IOException {
		String path = getDirectory("/sonyericsson");

		File myCaptureFile = new File(path + fileName);
		BufferedOutputStream bos = new BufferedOutputStream(
				new FileOutputStream(myCaptureFile));
		bm.compress(Bitmap.CompressFormat.JPEG, 75, bos);
		bos.flush();
		bos.close();
		return myCaptureFile.getPath();
	}

	/**
	 * 创建文件夹
	 * 
	 * @param path
	 * @return 文件夹路径
	 */
	public static String getDirectory(String path) {
		File downloadDir = new File(Environment.getExternalStorageDirectory(),
				path);
		if (!downloadDir.exists()) {
			downloadDir.mkdirs();
		}
		return downloadDir.getPath();
	}

	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(String filePath) {
		byte[] buffer = null;
		FileInputStream fis = null;
		ByteArrayOutputStream bos = null;
		try {
			File file = new File(filePath);
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] b = new byte[1024];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}

			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}

			if (bos != null) {
				try {
					bos.close();
				} catch (Exception e) {
				}
			}
		}
		return buffer;
	}


	/**
	 * 判断SD是否可以
	 *
	 * @return
	 */
	public static boolean isSdcardExist() {
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			return true;
		}
		return false;
	}

	/**
	 * 创建根目录
	 *
	 * @param path 目录路径
	 */
	public static void createDirFile(String path) {
		File dir = new File(path);
		if (!dir.exists()) {
			dir.mkdirs();
		}
	}

	/**
	 * 创建文件
	 *
	 * @param path 文件路径
	 * @return 创建的文件
	 */
	public static File createNewFile(String path) {
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				return null;
			}
		}
		return file;
	}

	/*-----add--------*/

	public static void saveFile(byte[] buf, String filePath, String fileName) {
		FileOutputStream fos = null;

		try {
			createDirectory(filePath);

			File file = new File(filePath + fileName);
			if (file.exists()) {
				file.delete();
			}


			fos = new FileOutputStream(file);
			fos.write(buf);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void createDirectory(String filePath) {
		if (filePath == null || filePath.equals("")) return;
		String tmpPath = File.separator;
		String[] paths = filePath.split(File.separator);
		for (int i = 0; i < paths.length; i++) {
			if (paths[i].equals("")) continue;
			tmpPath = tmpPath + File.separator + paths[i];
			File dir = new File(tmpPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
		}
	}

	//拷贝文件，从from拷贝到to中
	public static void copyFile(Context context, String from, String to) {
		try {
			InputStream myInput = context.getResources().getAssets().open(from);
			OutputStream myOutput = new FileOutputStream(to);
			byte[] buffer = new byte[1024];
			int length;
			while ((length = myInput.read(buffer)) > 0) {
				myOutput.write(buffer, 0, length);
			}
			myOutput.flush();
			myOutput.close();
			myInput.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/*--------Assets操作-------*/

	public static boolean copyAssetsPath(String AssetsPath, String ObjectPath, Context context) {
		File ObjPath = new File(ObjectPath);//创建一个文件
		if (!ObjPath.exists() || !ObjPath.isDirectory()) {
			ObjPath.mkdirs();//创建目录ObjPath
		}

		AssetManager am = context.getAssets();

		try {
			String[] FileOrDirName = am.list(AssetsPath);//Return a String array of all the assets at the given path.
			for (int i = 0; i < FileOrDirName.length; i++) {
				String path = "";
				if (AssetsPath != "") {
					path = AssetsPath + File.separator;
				}
				//如果是assets目录
				if (isAssetsDirs(AssetsPath + File.separator + FileOrDirName[i], context)) {
					File N_DIR = new File(ObjectPath + File.separator + FileOrDirName[i]);//创建file
					if (!N_DIR.exists()) {
						N_DIR.mkdir();//创建文件目录
					}

					copyAssetsPath(path + FileOrDirName[i], ObjPath + File.separator + FileOrDirName[i], context);//递归创建目录
				} else {
					FileUtils.copyFile(context, path + FileOrDirName[i], ObjPath + File.separator + FileOrDirName[i]);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;
	}


	private static boolean isAssetsDirs(String fileOrDirName, Context context) {
		AssetManager am = context.getAssets();

		try {
			am.open(fileOrDirName);//打开某个具体的asset文件，打不开的话应该是目录
			return false;
		} catch (FileNotFoundException e) {
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return true;
		}
	}

	/**
	 *  从assets目录中复制整个文件夹内容
	 *  @param  context  Context 使用CopyFiles类的Activity
	 *  @param  oldPath  String  原文件路径  如：/aa
	 *  @param  newPath  String  复制后路径  如：xx:/bb/cc
	 */
	public void copyFilesFassets(String oldPath,String newPath,Context context) {
		try {
			String fileNames[] = context.getAssets().list(oldPath);//获取assets目录下的所有文件及目录名
			if (fileNames.length > 0) {//如果是目录
				File file = new File(newPath);
				file.mkdirs();//如果文件夹不存在，则递归
				for (String fileName : fileNames) {
					copyFilesFassets(oldPath + "/" + fileName,newPath+"/"+fileName,context);
				}
			} else {//如果是文件
				InputStream is = context.getAssets().open(oldPath);
				FileOutputStream fos = new FileOutputStream(new File(newPath));
				byte[] buffer = new byte[1024];
				int byteCount=0;
				while((byteCount=is.read(buffer))!=-1) {//循环从输入流读取 buffer字节
					fos.write(buffer, 0, byteCount);//将读取的输入流写入到输出流
				}
				fos.flush();//刷新缓冲区
				is.close();
				fos.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*--------Assets操作-------*/

	public static byte[] strToByteArray(String str) {
		if (str == null) {
			return null;
		}
		byte[] byteArray = str.getBytes();
		return byteArray;
	}

}
