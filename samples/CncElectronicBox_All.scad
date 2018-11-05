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
}difference(){difference(){difference(){difference(){difference(){translate([46.1,46.75,16])cube([92.2,93.5,32],center=true);
translate([1,0,31])union() {
translate([-0.5,45.75,0.5])cube([1,10.4,1],center=true);
translate([90.7,45.75,0.5])cube([1,10.4,1],center=true);
}
}translate([46.1,46.75,16.5])cube([90.2,91.5,31],center=true);
}translate([0.5,33.7,5.15])cube([1,8,6.9],center=true);
}translate([0.5,47.6,7.2])cube([1,9.2,11],center=true);
}union() {
translate([4,4,0])cylinder(h=2.7, r=2.6, center=true);
translate([4,89.5,0])cylinder(h=2.7, r=2.6, center=true);
translate([88.2,89.5,0])cylinder(h=2.7, r=2.6, center=true);
translate([88.2,4,0])cylinder(h=2.7, r=2.6, center=true);
}
}translate([0,-101.5,0])difference(){union() {
translate([45.1,45.75,0.5])cube([90.2,91.5,1],center=true);
union() {
translate([-0.5,45.75,0.5])cube([1,10,1],center=true);
translate([90.7,45.75,0.5])cube([1,10,1],center=true);
}
}
translate([45.1,71.5,0.5])cube([25,7,1],center=true);
}}
