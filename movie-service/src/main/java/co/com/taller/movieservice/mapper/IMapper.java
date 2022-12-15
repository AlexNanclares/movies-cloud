package co.com.taller.movieservice.mapper;

public interface IMapper <Input, Output>{
    public Output map(Input in);
}
