package hse.kpo.domains.sales;

import hse.kpo.interfaces.sales.Sales;
import hse.kpo.interfaces.sales.SalesObserver;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * class of sales aspect.
 */
@Component
@Aspect
@RequiredArgsConstructor
public class SalesAspect {

    private final SalesObserver salesObserver;

    /**
     * functon to implement new fuctional.
     *
     * @param pjp point to entry.
     * @param sales annotation params.
     * @return result of code.
     * @throws Throwable if exeption.
     */
    @Around("@annotation(sales)")
    public Object sales(ProceedingJoinPoint pjp, Sales sales) throws Throwable {

        salesObserver.checkCustomers();

        String operationName = sales.value().isEmpty() ? pjp.getSignature().toShortString() : sales.value();
        try {
            Object result = pjp.proceed();
            salesObserver.checkCustomers();
            return result;
        } catch (Throwable e) {
            throw e;
        }
    }
}