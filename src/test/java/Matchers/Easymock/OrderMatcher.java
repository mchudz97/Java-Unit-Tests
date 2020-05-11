package Matchers.Easymock;

import org.easymock.EasyMock;
import org.easymock.IArgumentMatcher;
import proj2.entities.Order;

public class OrderMatcher {
    public static Order matchOrder(){
        EasyMock.reportMatcher(new IArgumentMatcher() {
            @Override
            public boolean matches(Object o) {
                return o instanceof Order && ((Order) o).getId() >= 0 && ((Order) o).getClientId() >= 0;
            }

            @Override
            public void appendTo(StringBuffer stringBuffer) {

                stringBuffer.append("Invalid id value.");

            }
        });


        return null;
    }

}
