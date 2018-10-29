$fs=0.25;$fa=6;
union() {
color([0.7686,0.1176,0.2275])union() {
union() {
difference(){cube([10,10,10],center=true);
union() {
rotate([90,0,0])cylinder(h=11, r=3, center=true);
rotate([0,90,0])cylinder(h=11, r=3, center=true);
}
}translate([0,0,5])intersection(){
sphere(r=7.0711);
translate([0,0,3.5355])cube([10,10,7.0711],center=true);
}
}
translate([0,0,-5])mirror([0,0,1])intersection(){
sphere(r=7.0711);
translate([0,0,3.5355])cube([10,10,7.0711],center=true);
}
}
color([0,0.498,1])rotate([90,0,0])cylinder(h=20, r=2, center=true);
translate([0,15,0])color([0,1,0.251])difference(){cube([20,3,25],center=true);
rotate([90,0,0])cylinder(h=4, r=5, center=true);
}translate([0,-15,0])color([0,1,0.251])difference(){cube([20,3,25],center=true);
rotate([90,0,0])cylinder(h=4, r=5, center=true);
}translate([0,-22,0])color([1,0.749,0])difference(){rotate([90,0,0])cylinder(h=3, r=5, center=true);
rotate([90,0,0])cylinder(h=4, r=3, center=true);
}translate([0,22,0])color([1,0.749,0])difference(){rotate([90,0,0])cylinder(h=3, r=5, center=true);
rotate([90,0,0])cylinder(h=4, r=3, center=true);
}}
