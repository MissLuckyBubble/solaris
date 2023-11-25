package uni.fmi.Solaris.rest;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uni.fmi.Solaris.dto.PaymentDTO;
import uni.fmi.Solaris.models.Order;
import uni.fmi.Solaris.models.Payment;
import uni.fmi.Solaris.services.OrderService;
import uni.fmi.Solaris.services.PaymentService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<PaymentDTO> findAll() {
        return paymentService.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping(path = "/{id}")
    public PaymentDTO getBy(@PathVariable(name = "id") long id) {
        return convertToDTO(paymentService.getEntity(id).get());
    }

    @PostMapping()
    public PaymentDTO create(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = convertToModel(paymentDTO);
        payment.setId(null);
        return convertToDTO(paymentService.create(payment));
    }

    @PutMapping()
    public PaymentDTO update(@RequestBody PaymentDTO paymentDTO) {
        Payment payment = convertToModel(paymentDTO);
        return convertToDTO(paymentService.update(payment));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<String> remove(@PathVariable(name = "id") long id) {

        boolean isRemoved = paymentService.remove(id);

        String deletedMessage = "Payment with id: '" + id + "' was deleted!";
        String notDeletedMessage = "Payment with id: '" + id + "' does not exists!";
        return isRemoved ?
                new ResponseEntity<>(deletedMessage, HttpStatusCode.valueOf(200)) :
                new ResponseEntity<>(notDeletedMessage, HttpStatusCode.valueOf(404));
    }

    private Payment convertToModel(PaymentDTO paymentDTO) {
        Payment payment = modelMapper.map(paymentDTO, Payment.class);
        Order order = orderService.getEntity(paymentDTO.getOrderId())
                .orElseThrow(() -> new IllegalStateException("wrong order id"));
        payment.setOrder(order);
        return payment;
    }

    private PaymentDTO convertToDTO(Payment payment) {
        PaymentDTO paymentDTO = modelMapper.map(payment, PaymentDTO.class);
        paymentDTO.setOrderId(payment.getOrder().getId());
        return paymentDTO;
    }
}
