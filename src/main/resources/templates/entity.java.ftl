package ${entityPackage};

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;
import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;

import lombok.Data;

/**
* ${desc}信息实体模型.
*
* @author ${author}
* @since ${date}
*/
@Data
@ApiModel(value = "${model + entitySuffix}", description = "${desc}信息实体模型")
public class ${model + entitySuffix} {

}