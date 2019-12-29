import {ConstantValueToStringConverter} from './constant-value-to-string-converter.service';

export class NumberToStringConverter implements ConstantValueToStringConverter<number> {

  convertValueToString(value: number): string {
    return value.toString();
  }
}
