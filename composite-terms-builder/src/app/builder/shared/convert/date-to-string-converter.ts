import {ConstantValueToStringConverter} from './constant-value-to-string-converter.service';

export class DateToStringConverter implements ConstantValueToStringConverter<Date> {

  convertValueToString(value: Date): string {
    return value.toLocaleDateString();
  }
}
