$fs=0.25;$fa=6;
union() {
difference(){translate([5,10,5])cube([10,20,10],center=true);
translate([5,10,5])sphere(r=10);
}union() {
cube([2,2,2],center=true);
translate([10,0,0])cube([2,2,2],center=true);
translate([10,20,0])cube([2,2,2],center=true);
translate([10,0,10])cube([2,2,2],center=true);
translate([10,20,10])cube([2,2,2],center=true);
translate([0,20,0])cube([2,2,2],center=true);
translate([0,20,10])cube([2,2,2],center=true);
translate([0,0,10])cube([2,2,2],center=true);
}
}
