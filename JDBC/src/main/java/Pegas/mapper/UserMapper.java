package Pegas.mapper;

public interface UserMapper<T,F> {
    T mapFrom(F from);
}
