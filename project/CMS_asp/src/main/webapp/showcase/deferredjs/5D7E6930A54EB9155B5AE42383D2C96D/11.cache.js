$wnd.showcase.runAsyncCallback11("function jjc(){}\nfunction ljc(){}\nfunction ejc(a,b){a.b=b}\nfunction fjc(a){if(a==Wic){return true}sB();return a==Zic}\nfunction gjc(a){if(a==Vic){return true}sB();return a==Uic}\nfunction kjc(a){this.b=(Nkc(),Ikc).a;this.e=(Skc(),Rkc).a;this.a=a}\nfunction djc(a,b){var c;c=ifb(a.fb,180);c.e=b.a;!!c.d&&$dc(c.d,b)}\nfunction cjc(a,b){var c;c=ifb(a.fb,180);c.b=b.a;!!c.d&&Ydc(c.d,b)}\nfunction hjc(){$ic();aec.call(this);this.b=(Nkc(),Ikc);this.c=(Skc(),Rkc);(Mac(),this.e)[ARc]=0;this.e[BRc]=0}\nfunction $ic(){$ic=vCb;Tic=new jjc;Wic=new jjc;Vic=new jjc;Uic=new jjc;Xic=new jjc;Yic=new jjc;Zic=new jjc}\nfunction _ic(a,b,c){var d;if(c==Tic){if(b==a.a){return}else if(a.a){throw RBb(new KBc('Only one CENTER widget may be added'))}}Rh(b);Yuc(a.j,b);c==Tic&&(a.a=b);d=new kjc(c);b.fb=d;cjc(b,a.b);djc(b,a.c);bjc(a);Th(b,a)}\nfunction ajc(a){var b,c,d,e,f,g,h;Fuc((Mac(),a.hb),'',qTc);g=new wJc;h=new gvc(a.j);while(h.b<h.c.c){b=evc(h);f=ifb(b.fb,180).a;d=ifb(EEc(OJc(g.d,f)),111);c=!d?1:d.a;e=f==Xic?'north'+c:f==Yic?'south'+c:f==Zic?'west'+c:f==Uic?'east'+c:f==Wic?'linestart'+c:f==Vic?'lineend'+c:fQc;Fuc(Qo(b.hb),qTc,e);QEc(g,f,XBc(c+1))}}\nfunction bjc(a){var b,c,d,e,f,g,h,i,j,k,l,m,n,o,p,q,r;b=(Mac(),a.d);while(occ(b)>0){wo(b,ncc(b,0))}o=1;e=1;for(i=new gvc(a.j);i.b<i.c.c;){d=evc(i);f=ifb(d.fb,180).a;f==Xic||f==Yic?++o:(f==Uic||f==Zic||f==Wic||f==Vic)&&++e}p=reb(nwb,bNc,289,o,0,1);for(g=0;g<o;++g){p[g]=new ljc;p[g].b=$doc.createElement(yRc);so(b,Tac(p[g].b))}k=0;l=e-1;m=0;q=o-1;c=null;for(h=new gvc(a.j);h.b<h.c.c;){d=evc(h);j=ifb(d.fb,180);r=$doc.createElement(zRc);j.d=r;j.d[mRc]=j.b;j.d.style[nRc]=j.e;j.d[tNc]=j.f;j.d[sNc]=j.c;if(j.a==Xic){Pac(p[m].b,r,p[m].a);so(r,Tac(d.hb));r[GSc]=l-k+1;++m}else if(j.a==Yic){Pac(p[q].b,r,p[q].a);so(r,Tac(d.hb));r[GSc]=l-k+1;--q}else if(j.a==Tic){c=r}else if(fjc(j.a)){n=p[m];Pac(n.b,r,n.a++);so(r,Tac(d.hb));r[rTc]=q-m+1;++k}else if(gjc(j.a)){n=p[m];Pac(n.b,r,n.a);so(r,Tac(d.hb));r[rTc]=q-m+1;--l}}if(a.a){n=p[m];Pac(n.b,c,n.a);so(c,Tac(eh(a.a)))}}\nvar qTc='cwDockPanel';uCb(446,1,eQc);_.Bc=function iVb(){var a,b,c;KEb(this.a,(a=new hjc,(Mac(),a.hb).className='cw-DockPanel',a.e[ARc]=4,ejc(a,(Nkc(),Hkc)),_ic(a,new Ghc('This is the first north component'),($ic(),Xic)),_ic(a,new Ghc('This is the first south component'),Yic),_ic(a,new Ghc('This is the east component'),Uic),_ic(a,new Ghc('This is the west component'),Zic),_ic(a,new Ghc('This is the second north component'),Xic),_ic(a,new Ghc('This is the second south component'),Yic),b=new Ghc(\"This is a <code>ScrollPanel<\\/code> contained at the center of a <code>DockPanel<\\/code>.  By putting some fairly large contents in the middle and setting its size explicitly, it becomes a scrollable area within the page, but without requiring the use of an IFRAME.<br><br>Here's quite a bit more meaningless text that will serve primarily to make this thing scroll off the bottom of its visible area.  Otherwise, you might have to make it really, really small in order to see the nifty scroll bars!\"),c=new _ec(b),c.hb.style[tNc]='400px',c.hb.style[sNc]='100px',_ic(a,c,Tic),ajc(a),a))};uCb(902,281,yNc,hjc);_.gc=function ijc(a){var b;b=Wcc(this,a);if(b){a==this.a&&(this.a=null);bjc(this)}return b};var Tic,Uic,Vic,Wic,Xic,Yic,Zic;var owb=oBc(wNc,'DockPanel',902);uCb(179,1,{},jjc);var lwb=oBc(wNc,'DockPanel/DockLayoutConstant',179);uCb(180,1,{180:1},kjc);_.c='';_.f='';var mwb=oBc(wNc,'DockPanel/LayoutData',180);uCb(289,1,{289:1},ljc);_.a=0;var nwb=oBc(wNc,'DockPanel/TmpRow',289);IMc(zl)(11);\n//# sourceURL=showcase-11.js\n")
