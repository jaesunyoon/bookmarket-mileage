package bookmarket;

import bookmarket.config.kafka.KafkaProcessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PolicyHandler{
    @StreamListener(KafkaProcessor.INPUT)
    public void onStringEventListener(@Payload String eventString){

    }

    @Autowired
    MileageRepository mileageRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverOrderCanceled_UseCancel(@Payload OrderCanceled orderCanceled){

        System.out.println("##### listener UseCancel : " + orderCanceled.toJson());
        if(orderCanceled.isMe() && "OrderCanceled".equals(orderCanceled.getStatus())){
            System.out.println("##### listener UseCancel : " + orderCanceled.toJson());
            List<Mileage> mileageList = mileageRepository.findByOrderId(orderCanceled.getId());
            for(Mileage mileage : mileageList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mileage.setStatus("useMileageCanceled");
                // view 레파지 토리에 save
                mileageRepository.save(mileage);
            }

        }

    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverShipped_UpdateMileage(@Payload Shipped shipped){

        if(shipped.isMe() && "Shipped".equals(shipped.getStatus()) && "N".equals(shipped.getIsMile())){
            System.out.println("##### listener UpdateMileage : " + shipped.toJson());

            Mileage mileage = new Mileage();
            mileage.setOrderId(shipped.getOrderId());
            mileage.setCustomerId(shipped.getCustomerId());
            mileage.setStatus("getMileage");
            mileage.setIsMile("N");

            mileageRepository.save(mileage);
        }
    }
    @StreamListener(KafkaProcessor.INPUT)
    public void wheneverDeliveryCanceled_UpdateMileage(@Payload DeliveryCanceled deliveryCanceled){

        if(deliveryCanceled.isMe() && "ShipCanceled".equals(deliveryCanceled.getStatus())&& "N".equals(deliveryCanceled.getIsMile())){
            System.out.println("##### listener UpdateMileage : " + deliveryCanceled.toJson());
            List<Mileage> mileageList = mileageRepository.findByOrderId(deliveryCanceled.getOrderId());
            for(Mileage mileage : mileageList){
                // view 객체에 이벤트의 eventDirectValue 를 set 함
                mileage.setStatus("getMileageCanceled");
                // view 레파지 토리에 save
                mileageRepository.save(mileage);
            }
        }
    }

}
