package order.processor;

import order.model.Order;
import order.model.ProcessingResult;

public interface OrderProcessor {

    String getCategory();

    ProcessingResult process(Order order);
}
