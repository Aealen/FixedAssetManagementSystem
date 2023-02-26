package tech.aowu.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: TODO
 * @ClassName: OrderView
 * @Author: Aealen
 * @Date: 2023/2/25 13:49
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderView {


    private Long id;
    private String orderDesc;
    private Long faId;
    private String faName;
    private Long faTid;
    private String faTypeName;
    private String faModel;
    private Double faPrice;
    private Long faDid;
    private Long orderWorker;
    private String orderWorkerNickname;
    private String faDeptName;
    private Long orderCustodianId;
    private String orderCustodian;
    private Integer orderStatus;
    private String orderReporter;
    private Long orderReporterId;
    private String orderUpTime;
}
