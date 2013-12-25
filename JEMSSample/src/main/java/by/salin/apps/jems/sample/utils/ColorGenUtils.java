package by.salin.apps.jems.sample.utils;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by alexander.salin on 19.12.13.
 */
public class ColorGenUtils
{
	public static int generateColor()
	{
		Random random = new Random();
		return Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

}
