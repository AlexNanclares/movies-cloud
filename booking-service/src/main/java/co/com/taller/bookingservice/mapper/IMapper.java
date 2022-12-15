package co.com.taller.bookingservice.mapper;

public interface IMapper <Input, Output>{
    public Output map(Input in);
}
