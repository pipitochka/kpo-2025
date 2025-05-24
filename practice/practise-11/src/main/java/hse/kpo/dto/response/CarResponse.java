package hse.kpo.dto.response;

/**
 * record for car response.
 *
 * @param vin num of vim.
 * @param engineType type of engine.
 * @param pedalSize int size of pedal.
 */
public record CarResponse(
        Integer vin,
        String engineType,
        Integer pedalSize
) {}