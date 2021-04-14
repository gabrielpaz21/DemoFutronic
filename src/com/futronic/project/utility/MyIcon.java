package com.futronic.project.utility;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;

public class MyIcon implements Icon {

	public MyIcon(int width , int height) {
		this.width=width;
		this.height=height;
		m_Image = null;
	}

	public void paintIcon(Component c, Graphics g, int x, int y) {
		if (m_Image != null) {
			g.drawImage(m_Image, x, y, getIconWidth(), getIconHeight(), null);
		} else {
			g.fillRect(x, y, getIconWidth(), getIconHeight());
		}
	}

	public int getIconWidth() {
		return width;
	}

	public int getIconHeight() {
		return height;
	}
	
	public Image getImage(){
		
		return m_Image;
	}

	public boolean LoadImage(String path) {
		boolean bRetCode = false;
		Image newImg;

		try {
			File f = new File(path);
			newImg = ImageIO.read(f);
			bRetCode = true;
			setImage(newImg);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return bRetCode;
	}

	public void setImage(Image Img) {

		if (Img != null) {
			m_Image = Img.getScaledInstance(getIconWidth(), getIconHeight(), Image.SCALE_FAST);
		} else {
			m_Image = null;
		}
	}
	
	private int width;
	private int height;
	private Image m_Image;
}
