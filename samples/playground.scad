$fs=0.25;$fa=6;
difference(){cube([10,10,10],center=true);
union() {
union() {
cylinder(h=11, r=3, center=true);
rotate([90,0,0])cylinder(h=11, r=3, center=true);
}
rotate([0,90,0])cylinder(h=11, r=3, center=true);
}
}