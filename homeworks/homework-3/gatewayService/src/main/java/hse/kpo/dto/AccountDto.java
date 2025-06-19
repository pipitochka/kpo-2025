package hse.kpo.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AccountDto {
    private final Long id;

    private final String name;

    private final double balance;
}
