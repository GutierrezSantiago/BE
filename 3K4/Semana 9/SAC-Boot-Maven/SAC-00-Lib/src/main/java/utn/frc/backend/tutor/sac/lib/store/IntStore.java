package utn.frc.backend.tutor.sac.lib.store;

public class IntStore<ET extends Updateable<ET>> extends Store<ET, Integer> {
    private Integer id = 0;

    @Override
    protected Integer nextKey() {
        if (!incrementable) {
            throw new UnsupportedOperationException("Operación no permitida.");
        }
        return ++id;
    }

    public IntStore(boolean isolated, boolean incrementable) {
        super(isolated, incrementable);
    }

    public IntStore(boolean isolated) {
        this(isolated, false);
    }

    public IntStore() {
        super();
    }

    @Override
    public void clear() {
        data.clear();
        id = 0;
    }

}
