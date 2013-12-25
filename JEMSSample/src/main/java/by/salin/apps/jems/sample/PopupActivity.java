/**
 *
 */
package by.salin.apps.jems.sample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.FrameLayout;
import by.salin.apps.jems.JEMS;
import by.salin.apps.jems.impl.Event;
import by.salin.apps.jems.sample.base.BaseActivity;
import by.salin.apps.jems.sample.events.ChangeColorEvent;
import by.salin.apps.jems.sample.events.SpeedChangeEvent;
import by.salin.apps.jems.sample.utils.ColorGenUtils;
import by.salin.apps.jems.sample.utils.PopupActivityUtil;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * @author alexander.salin
 */
@EActivity(R.layout.ac_popup)
public class PopupActivity extends BaseActivity
{
	private static final String PERCENTAGE = "%";
	private static final String CURRENT_SPEED_VALUME = "Current speed valume : ";
	private static final String DEFAULT_TITLE = "Hello handler";
	private static final String FRAGMENT_TITLE = "Main popup fragment";

	@ViewById(R.id.container)
	FrameLayout container;

	private boolean isAdded = false;

	@Override
	protected void onCreate(Bundle instanceState)
	{

		super.onCreate(instanceState);
		float partWidth = 0.5f;
		float partHeight = 0.5f;
		PopupActivityUtil.getInstance(this).setParams(partWidth, partHeight);

	}

	@AfterViews
	protected void init()
	{
		Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.container);
		if (fragment == null)
		{
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.container, SampleFragment.newInstance(FRAGMENT_TITLE))
					.addToBackStack(null).commit();
		}
		setTitle(DEFAULT_TITLE);
	}

	@Override
	public void onEvent(Event event)
	{
		if (event instanceof SpeedChangeEvent)
		{
			setTitle(CURRENT_SPEED_VALUME + ((SpeedChangeEvent) event).getSpeedValume() + PERCENTAGE);
		}
		else if (event instanceof ChangeColorEvent)
		{
			container.setBackgroundColor(ColorGenUtils.generateColor());
		}
	}

}
