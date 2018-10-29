$fs=0.25;$fa=6;
union() {
color([0.7686,0.1176,0.2275])cube([10,10,10],center=true);
translate([0,0,5])color([0,0.498,1])intersection(){
sphere(r=7.0711);
translate([0,0,3.5355])cube([10,10,7.0711],center=true);
}
translate([0,0,-5])color([0,0.498,1])mirror([0,0,1])intersection(){
sphere(r=7.0711);
translate([0,0,3.5355])cube([10,10,7.0711],center=true);
}
}
