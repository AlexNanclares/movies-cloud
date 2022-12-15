package co.com.taller.userservice.mapper;

public interface IMapper <Input, Output>{
    public Output map(Input in);
}
