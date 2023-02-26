package tech.aowu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: TODO
 * @ClassName: FAView
 * @Author: Aealen
 * @Date: 2023/2/18 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FAView {
    private Long id;
    private String faName;
    private String faModel;
    private String faProducer;
    private Double faPrice;
    private Long faTid;
    private String faTypeName;
    private Long faDid;
    private String faDeptName;
    private Long faCustodianId;
    private String faCustodian;
}
