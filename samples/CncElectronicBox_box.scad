difference(){
difference(){
difference(){
translate([47.6, 49.75, 16.0]) cube([95.2, 99.5, 32.0], center = true);
translate([47.6, 49.75, 16.5]) cube([93.2, 97.5, 31.0], center = true);
}
union() {
translate([0.5, 49.75, 31.5]) cube([1.0, 10.6, 1.0], center = true);
translate([94.7, 49.75, 31.5]) cube([1.0, 10.6, 1.0], center = true);
}
}
union() {
union() {
translate([0.5, 37.15, 9.85]) cube([1.0, 8.3, 4.3], center = true);
translate([0.5, 50.5, 13.2]) cube([1.0, 9.0, 11.0], center = true);
}
union() {
translate([7.0, 14.0, 0.5]) cylinder(h = 1.0, r = 2.6, center = true);
translate([7.0, 90.0, 0.5]) cylinder(h = 1.0, r = 2.6, center = true);
translate([85.0, 90.0, 0.5]) cylinder(h = 1.0, r = 2.6, center = true);
translate([85.0, 14.0, 0.5]) cylinder(h = 1.0, r = 2.6, center = true);
}
}
}