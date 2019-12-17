import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {DefaultTermBuilderComponent, DefaultTermBuilderInteractionsComponent} from './builder/default';
import {DemoComponent} from './demo.component';
import {TreeModule} from 'angular-tree-component';

@NgModule({
  declarations: [
    DemoComponent,
    DefaultTermBuilderComponent,
    DefaultTermBuilderInteractionsComponent
  ],
  exports: [
    DefaultTermBuilderComponent,
    DefaultTermBuilderInteractionsComponent
  ],
  imports: [
    BrowserModule,
    TreeModule.forRoot()
  ],
  providers: [],
  bootstrap: [DemoComponent]
})
export class TermsModule {
}
