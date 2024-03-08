package br.com.mayara.mapper;


//import com.github.dozermapper.core.DozerBeanMapperBuilder;
//import com.github.dozermapper.core.Mapper;

import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;

public class DozerMapper {

//    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    private static ModelMapper mapper = new ModelMapper();

    public static <O, D> D parseObject(O origem, Class<D> destination){

        return mapper.map(origem, destination);
    }

    public static <O, D> List<D> parseListObjects(List<O> origem, Class<D> destination){
        List<D> destinationObjects = new ArrayList<>();
        for (O o : origem) {
            destinationObjects.add(mapper.map(o, destination));

        }
        return destinationObjects;
    }

//    public PersonVO toDTO(final Person model){
//        PersonVO personVO = PersonVO.builder()
//                .firstName(model.getFirstName())
//                .lastName(model.getLastName())
//                .address(model.getAddress())
//                .gender(model.getGender())
//                .build();
//        return personVO;
//    }
}
