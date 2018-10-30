$fs=0.25;$fa=6;
color([1,0.749,0])union() {
cylinder(h=3, r=23, center=true);
difference(){difference(){union() {
translate([0,0,5.75])cylinder(h=8.5, r=10, center=true);
difference(){intersection(){
translate([0,0,8.5])cylinder(h=3, r1=13, r2=10, center=true);
union() {
translate([0,10,8.5])rotate([0,0,-90])cylinder(h=3, r=10, $fn=3, center=true);
translate([0,-10,8.5])mirror([0,1,0])rotate([0,0,-90])cylinder(h=3, r=10, $fn=3, center=true);
}
}
union() {
translate([0,0,5.75])rotate([0,0,60])cube([27,3,8.5],center=true);
translate([0,0,5.75])rotate([0,0,-60])cube([27,3,8.5],center=true);
}
}}
translate([0,0,5.75])cylinder(h=8.5, r=8, center=true);
}union() {
translate([0,0,5.75])rotate([0,0,60])cube([27,3,8.5],center=true);
translate([0,0,5.75])rotate([0,0,-60])cube([27,3,8.5],center=true);
}
}}
