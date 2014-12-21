/** 基于世界之境(http://sekai.agrp.info)世界观的一个简易Stg
 * 作者：大地无敌、贝祥舜、Marco
 * Some Rights Reserved.
 */
package deltastg;

import java.awt.Font;
import java.io.InputStream;
import java.io.IOException;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.*;


/** 绘制封装类 静态
 * @author 大地无敌
 * 最后修改12/25/2012
 */
public class Renderer {
	

	
	/** 初始化opengl 2D绘制模式
	 * @param width 视图宽
	 * @param height 视图高
	 */
	public static void initialize2D(int width,int height)
	{
		Renderer.width = width;
		Renderer.height = height;
		GL11.glEnable(GL11.GL_TEXTURE_2D);               
        
		//设置清屏颜色
		GL11.glClearColor(57/255f, 172/255f, 242/255f, 0.0f);          
        
        // 开启 alpha混合
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	/** 绘制图片
	 * @param texture 纹理
	 * @param x 左上所在X坐标
	 * @param y 左上所在Y坐标
	 * @param width 宽度
	 * @param height 高度
	 */
	public static void drawImage(Texture texture,int x,int y,int width,int height)
	{
		Color.white.bind();
		texture.bind(); 
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+width,y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+width,y+height);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x,y+height);
		GL11.glEnd();
		
	}
	
	/** 绘制图片
	 * @param texture 纹理
	 * @param x 左上所在X坐标
	 * @param y 左上所在Y坐标
	 */
	public static void drawImage(Texture texture,int x,int y,Color color)
	{
		color.bind();
		texture.bind(); 
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x,y);
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+texture.getTextureWidth(),y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+texture.getTextureWidth(),y+texture.getTextureHeight());
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x,y+texture.getTextureHeight());
		GL11.glEnd();
		
		//GL11.glClear(0);
		
	}
	
	/** 绘制图片（以中心为基准点）
	 * @param texture 纹理
	 * @param x 中心所在X坐标
	 * @param y 中心所在Y坐标
	 * @param scale 缩放比率
	 */
	public static void drawImageCentered(Texture texture,int x,int y,float scale,Color color)
	{
		color.bind();
		texture.bind(); 
		int offsetX = (int)(texture.getTextureWidth()*scale*0.5f);//X偏移
		int offsetY = (int)(texture.getTextureHeight()*scale*0.5f);//Y偏移
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x-offsetX,y-offsetY);//嘛，还是依次绘出四个角
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+offsetX,y-offsetY);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+offsetX,y+offsetY);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x-offsetX,y+offsetY);
		GL11.glEnd();
		
	}
	
	/** 绘制图片（以中心为基准点）
	 * @param texture 纹理
	 * @param x 中心所在X坐标
	 * @param y 中心所在Y坐标
	 * @param scale 缩放比率
	 * @param rotation 旋转角 弧度，由x轴正向y轴正旋转
	 */
	public static void drawImageCentered(Texture texture,int x,int y,float scale,Color color,float rotation)
	{
		color.bind();
		texture.bind(); 
		float offsetX = (texture.getTextureWidth()*scale*0.5f);//X偏移
		float offsetY = (texture.getTextureHeight()*scale*0.5f);//Y偏移
		
		//得到中心向四个角的平移向量 四维是为了旋转矩阵作准备
		Vector4f offset1 = new Vector4f(-offsetX,-offsetY,0,0);
		Vector4f offset2 = new Vector4f(offsetX,-offsetY,0,0);
		Vector4f offset3 = new Vector4f(offsetX,offsetY,0,0);
		Vector4f offset4 = new Vector4f(-offsetX,offsetY,0,0);
		
		//旋转矩阵生成
		Matrix4f rotationM = new Matrix4f();
		rotationM.setIdentity(); //设置为恒等矩阵
		rotationM.rotate(rotation, new Vector3f(0,0,1));//生成旋转矩阵
		
		//矩阵变换生成中心到四个角的平移向量
		Matrix4f.transform(rotationM, offset1, offset1);
		Matrix4f.transform(rotationM, offset2, offset2);
		Matrix4f.transform(rotationM, offset3, offset3);
		Matrix4f.transform(rotationM, offset4, offset4);
		
		
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x+offset1.x,y+offset1.y);//嘛，还是依次绘出四个角
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+offset2.x,y+offset2.y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+offset3.x,y+offset3.y);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x+offset4.x,y+offset4.y);
		GL11.glEnd();
		
	}
	
	/** 读取png
	 * @param location 文件位置
	 * @return 纹理
	 */
	public static Texture LoadPNG(String location)
	{
		try
		{
			return TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(location));
		}
		catch(Exception e){
			return null;
		}
	}
	
	/** 得到画面宽度
	 * @return the width
	 */
	public static int getWidth() {
		return width;
	}

	/**得到画面高度
	 * @return the height
	 */
	public static int getHeight() {
		return height;
	}
	
	public static void Drawquad(int x,int y,int w,int h,Color color)
	{
		color.bind();
		blank.bind();

			
		// draw quad
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
		    GL11.glVertex2f(x,y);
		    GL11.glTexCoord2f(1,0);
		    GL11.glVertex2f(x+w,y);
		    GL11.glTexCoord2f(1,1);
		    GL11.glVertex2f(x+w,y+h);
		    GL11.glTexCoord2f(0,1);
		    GL11.glVertex2f(x,y+h);
		GL11.glEnd();
	}

	
	
	/**当前字体
	 * 
	 */
	public static UnicodeFont currentFont;// = new UnicodeFont(new Font("Times New Roman", Font.BOLD, 24));
	public static Texture blank = LoadPNG("res/blank.png");
	
	private static int width = 1280;
	private static int height = 720;
	

	static{
		//初始化Renderer
		
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/font1.ttf");
			
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(24f);
			currentFont = new UnicodeFont(awtFont,24,true,false);
			currentFont.addGlyphs(0, 65535);//添加字符
			currentFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
			currentFont.loadGlyphs();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
