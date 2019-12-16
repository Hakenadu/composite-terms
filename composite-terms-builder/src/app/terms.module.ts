import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {D3TermBuilderComponent} from './builder/d3';
import {FormTermBuilderComponent} from './builder/form';
import {DemoComponent} from './demo.component';

@NgModule({
  declarations: [
    DemoComponent,
    D3TermBuilderComponent,
    FormTermBuilderComponent
  ],
  exports: [
    D3TermBuilderComponent,
    FormTermBuilderComponent
  ],
  imports: [
    BrowserModule
  ],
  providers: [],
  bootstrap: [DemoComponent]
})
export class TermsModule {
}
