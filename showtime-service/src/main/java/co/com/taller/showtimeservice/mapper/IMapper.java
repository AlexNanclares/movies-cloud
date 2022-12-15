package co.com.taller.showtimeservice.mapper;

import java.util.List;

public interface IMapper <Input, Output>{
    public Output map(Input in);
}
