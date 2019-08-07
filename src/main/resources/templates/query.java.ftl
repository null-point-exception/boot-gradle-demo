package ${queryPackage};

import com.practice.bean.query.Sort;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
* ${desc}查询模型.
*
* @author ${author}
* @since ${date}
*/
@Data
@ApiModel(value = "${model + querySuffix}", description = "${desc}查询模型")
public class ${model + querySuffix} {

    /**
    * 排序
    */
    @ApiModelProperty(position = 99, dataType = "Sort", value = "排序", name = "sort")
    private Sort sort;

}