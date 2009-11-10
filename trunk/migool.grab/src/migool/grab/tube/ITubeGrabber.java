package migool.grab.tube;

import migool.grab.IGrabber;

/**
 * 
 * @author Denis Migol
 * 
 */
public interface ITubeGrabber extends IGrabber {
	boolean hasNext();

	boolean hasPrev();

	ITubeGrab next();

	ITubeGrab prev();

	ITubePosition getTubePosition();

	void setTubePosition(ITubePosition tubePosition);
}
