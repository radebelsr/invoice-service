package za.co.bmw.invoice.mapper;

import org.springframework.beans.BeanUtils;
import za.co.bmw.invoice.domain.LineItem;
import za.co.bmw.invoice.dto.LineItemDTO;

public class LineItemMapper {
    public static LineItem fromDTO(LineItemDTO lineItemDTO){
        LineItem lineItem = new LineItem();
        BeanUtils.copyProperties(lineItemDTO, lineItem);
        return lineItem;
    }

    public static LineItemDTO toDTO(LineItem lineItem){
        LineItemDTO lineItemDTO = new LineItemDTO();
        BeanUtils.copyProperties(lineItem,lineItemDTO);
        lineItemDTO.setTotal(lineItem.getLineItemTotal());
        return lineItemDTO;
    }
}
