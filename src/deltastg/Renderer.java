/** ��������֮��(http://sekai.agrp.info)����۵�һ������Stg
 * ���ߣ�����޵С�����˴��Marco
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


/** ���Ʒ�װ�� ��̬
 * @author ����޵�
 * ����޸�12/25/2012
 */
public class Renderer {
	

	
	/** ��ʼ��opengl 2D����ģʽ
	 * @param width ��ͼ��
	 * @param height ��ͼ��
	 */
	public static void initialize2D(int width,int height)
	{
		Renderer.width = width;
		Renderer.height = height;
		GL11.glEnable(GL11.GL_TEXTURE_2D);               
        
		//����������ɫ
		GL11.glClearColor(57/255f, 172/255f, 242/255f, 0.0f);          
        
        // ���� alpha���
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        
        GL11.glViewport(0,0,width,height);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
	}
	
	/** ����ͼƬ
	 * @param texture ����
	 * @param x ��������X����
	 * @param y ��������Y����
	 * @param width ���
	 * @param height �߶�
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
	
	/** ����ͼƬ
	 * @param texture ����
	 * @param x ��������X����
	 * @param y ��������Y����
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
	
	/** ����ͼƬ��������Ϊ��׼�㣩
	 * @param texture ����
	 * @param x ��������X����
	 * @param y ��������Y����
	 * @param scale ���ű���
	 */
	public static void drawImageCentered(Texture texture,int x,int y,float scale,Color color)
	{
		color.bind();
		texture.bind(); 
		int offsetX = (int)(texture.getTextureWidth()*scale*0.5f);//Xƫ��
		int offsetY = (int)(texture.getTextureHeight()*scale*0.5f);//Yƫ��
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x-offsetX,y-offsetY);//��������λ���ĸ���
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+offsetX,y-offsetY);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+offsetX,y+offsetY);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x-offsetX,y+offsetY);
		GL11.glEnd();
		
	}
	
	/** ����ͼƬ��������Ϊ��׼�㣩
	 * @param texture ����
	 * @param x ��������X����
	 * @param y ��������Y����
	 * @param scale ���ű���
	 * @param rotation ��ת�� ���ȣ���x������y������ת
	 */
	public static void drawImageCentered(Texture texture,int x,int y,float scale,Color color,float rotation)
	{
		color.bind();
		texture.bind(); 
		float offsetX = (texture.getTextureWidth()*scale*0.5f);//Xƫ��
		float offsetY = (texture.getTextureHeight()*scale*0.5f);//Yƫ��
		
		//�õ��������ĸ��ǵ�ƽ������ ��ά��Ϊ����ת������׼��
		Vector4f offset1 = new Vector4f(-offsetX,-offsetY,0,0);
		Vector4f offset2 = new Vector4f(offsetX,-offsetY,0,0);
		Vector4f offset3 = new Vector4f(offsetX,offsetY,0,0);
		Vector4f offset4 = new Vector4f(-offsetX,offsetY,0,0);
		
		//��ת��������
		Matrix4f rotationM = new Matrix4f();
		rotationM.setIdentity(); //����Ϊ��Ⱦ���
		rotationM.rotate(rotation, new Vector3f(0,0,1));//������ת����
		
		//����任�������ĵ��ĸ��ǵ�ƽ������
		Matrix4f.transform(rotationM, offset1, offset1);
		Matrix4f.transform(rotationM, offset2, offset2);
		Matrix4f.transform(rotationM, offset3, offset3);
		Matrix4f.transform(rotationM, offset4, offset4);
		
		
		
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glTexCoord2f(0,0);
			GL11.glVertex2f(x+offset1.x,y+offset1.y);//��������λ���ĸ���
			GL11.glTexCoord2f(1,0);
			GL11.glVertex2f(x+offset2.x,y+offset2.y);
			GL11.glTexCoord2f(1,1);
			GL11.glVertex2f(x+offset3.x,y+offset3.y);
			GL11.glTexCoord2f(0,1);
			GL11.glVertex2f(x+offset4.x,y+offset4.y);
		GL11.glEnd();
		
	}
	
	/** ��ȡpng
	 * @param location �ļ�λ��
	 * @return ����
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
	
	/** �õ�������
	 * @return the width
	 */
	public static int getWidth() {
		return width;
	}

	/**�õ�����߶�
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

	
	
	/**��ǰ����
	 * 
	 */
	public static UnicodeFont currentFont;// = new UnicodeFont(new Font("Times New Roman", Font.BOLD, 24));
	public static Texture blank = LoadPNG("res/blank.png");
	
	private static int width = 1280;
	private static int height = 720;
	

	static{
		//��ʼ��Renderer
		
		try {
			InputStream inputStream	= ResourceLoader.getResourceAsStream("res/font1.ttf");
			
			Font awtFont = Font.createFont(Font.TRUETYPE_FONT, inputStream);
			awtFont = awtFont.deriveFont(24f);
			currentFont = new UnicodeFont(awtFont,24,true,false);
			currentFont.addGlyphs(0, 65535);//����ַ�
			currentFont.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
			currentFont.loadGlyphs();
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
}
