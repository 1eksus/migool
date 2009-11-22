package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 *
 */
public interface ITubeGrabberManager {
	boolean hasNext();

	boolean hasPrev();

	ITubeGrab next();

	ITubeGrab prev();

	ITubePosition getTubePosition();

	void setTubePosition(ITubePosition tubePosition);
}
