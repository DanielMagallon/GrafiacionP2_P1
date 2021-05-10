package lib.staticlass;

public enum AnimationStatus
{
    NONE(2),
    SUSPEND(0),
    STOP(-1),
    START(1);

    public int status;

    AnimationStatus(int status){
        this.status  = status;
    }
}
