package migool.grab.tube;

/**
 * 
 * @author Denis Migol
 * 
 */
public abstract class TubeGrabberBase implements ITubeGrabber {

	protected ITubePosition tubePosition;

	@Override
	public ITubePosition getTubePosition() {
		return tubePosition;
	}

	@Override
	public void setTubePosition(ITubePosition tubePosition) {
		this.tubePosition = tubePosition;
		updateTubePosition();
	}

	protected abstract void updateTubePosition();

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean hasPrev() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ITubeGrab next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ITubeGrab prev() {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
//	public String getHost() {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
