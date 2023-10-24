package utn.frc.backend.tutor.sac.lib.store;

// todo: discutir interfaz "Cloneable".
//       Shallow copy. Deep copy.
//       CloneNotSupportedException.
//       etc...

public interface Updateable<ET> {
    public ET clone();
    public ET update(ET from);
}
