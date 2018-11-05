$fs=0.25;$fa=6;
union() {
translate([-15.5,10,2])difference(){cylinder(h=4, r=5.5, center=true);
cylinder(h=4, r=2.6, center=true);
}translate([-15.5,26,2])difference(){cylinder(h=4, r=5.5, center=true);
cylinder(h=4, r=2.6, center=true);
}translate([-15.5,42,2])difference(){cylinder(h=4, r=5.5, center=true);
cylinder(h=4, r=2.6, center=true);
}translate([-15.5,58,2])difference(){cylinder(h=4, r=5.5, center=true);
cylinder(h=4, r=2.6, center=true);
}difference(){difference(){difference(){translate([51.1,51.75,16])cube([102.2,103.5,32],center=true);
translate([51.1,51.75,16.5])cube([100.2,101.5,31],center=true);
}union() {
translate([0.5,51.75,31.5])cube([1,10.6,1],center=true);
translate([101.7,51.75,31.5])cube([1,10.6,1],center=true);
}
}union() {
union() {
translate([0.5,33.7,4.7])cube([1,8,4],center=true);
translate([0.5,47.5,8.2])cube([1,9,11],center=true);
}
union() {
translate([12,16,0.5])cylinder(h=1, r=2.6, center=true);
translate([12,92,0.5])cylinder(h=1, r=2.6, center=true);
translate([90,92,0.5])cylinder(h=1, r=2.6, center=true);
translate([90,16,0.5])cylinder(h=1, r=2.6, center=true);
}
}
}translate([0,-111.5,0])difference(){union() {
translate([50.1,50.75,0.5])cube([100.2,101.5,1],center=true);
union() {
translate([-0.5,50.75,0.5])cube([1,10,1],center=true);
translate([100.7,50.75,0.5])cube([1,10,1],center=true);
}
}
translate([50.1,81.5,0.5])cube([25,7,1],center=true);
}}
