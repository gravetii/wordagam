//		[min, t1, t2, t3, t4, max]; max for upper bound of bar scale
// 		 0    1   2   3   4   5
//Ex.:	 0    10   15   40   70   100
var classMetricSpec= [
{shortname: "LOC", name: "Class Lines of Code",thresholds:[0, 50, 300, 900, 1500, 2000]},
{shortname: "NOM", name: "Number of Methods",thresholds:[0, 20, 30, 40, 50, 60]},
{shortname: "NOF", name: "Number of Fields",thresholds:[0, 20, 30, 40, 50, 60]},
{shortname: "WMC", name: "Weighted Method Count",thresholds:[0, 20, 50, 101, 120, 150]},
{shortname: "RFC", name: "Response For a Class",thresholds:[0, 50, 100, 150, 200, 250]},
{shortname: "SRFC", name: "Simple Response For a Class",thresholds:[0, 50, 100, 150, 200, 250]},
{shortname: "DIT", name: "Depth of Inheritance Tree",thresholds:[0, 1, 3, 10, 20, 30]},
{shortname: "CBO", name: "Coupling Between Object Classes",thresholds:[0, 5, 10, 20, 30, 50]},
{shortname: "ATFD", name: "Access to Foreign Data",thresholds:[0, 1, 3, 5, 10, 20]},
{shortname: "SI", name: "Specialization Index",thresholds:[0.0, 0.1, 0.3, 0.4, 0.7, 1.2]},
{shortname: "LCAM", name: "Lack of Cohesion Among Methods(1-CAM)",thresholds:[0.0, 0.6, 0.7, 0.8, 0.9, 1.0]},
{shortname: "NOC", name: "Number of Children",thresholds:[0, 1, 5, 10, 15, 20]},
{shortname: "LTCC", name: "Lack of Tight Class Cohesion",thresholds:[0.0, 0.6, 0.7, 0.8, 0.9, 1.0]},
{shortname: "LCOM", name: "Lack of Cohesion of Methods",thresholds:[0.0, 0.5, 0.7, 0.8, 1.0, 2.0]}];

var classMetricValues= [
{name: "Class Lines of Code", value:300},
{name: "Number of Methods", value:30},
{name: "Number of Fields", value:30},
{name: "Weighted Method Count", value:50},
{name: "Response For a Class", value:100},
{name: "Simple Response For a Class", value:100},
{name: "Depth of Inheritance Tree", value:3},
{name: "Coupling Between Object Classes", value:10},
{name: "Access to Foreign Data", value:3},
{name: "Specialization Index", value:0.3},
{name: "Lack of Cohesion Among Methods(1-CAM)", value:0.7},
{name: "Number of Children", value:5},
{name: "Lack of Tight Class Cohesion", value:0.7},
{name: "Lack of Cohesion of Methods", value:0.7}];

var packageMetricSpec= [
{shortname: "#(C&I)", name: "Number of Entities",thresholds:[0, 5, 10, 30, 40, 50]},
{shortname: "#C", name: "Number of Classes",thresholds:[0, 5, 10, 20, 30, 50]},
{shortname: "#I", name: "Number of Interfaces",thresholds:[0, 5, 10, 20, 30, 50]},
{shortname: "LOC", name: "Class Lines of Code",thresholds:[0, 250, 1500, 4500, 7500, 10000]},
{shortname: "AC", name: "Afferent Coupling",thresholds:[0, 5, 10, 20, 100, 150]},
{shortname: "EC", name: "Efferent Coupling",thresholds:[0, 5, 10, 20, 100, 150]},
{shortname: "Abs", name: "Abstractness",thresholds:[0.0, 0.1, 0.2, 0.3, 0.4, 1.0]},
{shortname: "Ins", name: "Instability",thresholds:[0.0, 0.1, 0.2, 0.3, 0.4, 1.0]},
{shortname: "ND", name: "Normalized Distance",thresholds:[0.0, 0.1, 0.2, 0.3, 0.4, 1.0]},
{shortname: "WMC", name: "Weighted Method Count",thresholds:[0, 200, 500, 1000, 2000, 2000]}];

var packageMetricValues= [
{name: "Number of Entities", value:10},
{name: "Number of Classes", value:10},
{name: "Number of Interfaces", value:10},
{name: "Class Lines of Code", value:1500},
{name: "Afferent Coupling", value:10},
{name: "Efferent Coupling", value:10},
{name: "Abstractness", value:0.2},
{name: "Instability", value:0.2},
{name: "Normalized Distance", value:0.2},
{name: "Weighted Method Count", value:500}];

var methodMetricSpec= [
{shortname: "MCC", name: "McCabe Cyclomatic Complexity",thresholds:[0, 10, 17, 20, 25, 30]},
{shortname: "NBD", name: "Nested Block Depth",thresholds:[0, 1, 2, 4, 5, 10]},
{shortname: "LOC", name: "Method Lines of Code",thresholds:[0, 30, 50, 100, 200, 300]},
{shortname: "#Pa", name: "Number of Parameters",thresholds:[0, 3, 5, 6, 8, 10]},
{shortname: "#MC", name: "Number of Methods Called",thresholds:[0, 3, 5, 6, 8, 10]},
{shortname: "#AF", name: "Number of Accessed Fields",thresholds:[0, 3, 5, 6, 8, 10]}];

var methodMetricValues= [
{name: "McCabe Cyclomatic Complexity", value:17},
{name: "Nested Block Depth", value:2},
{name: "Method Lines of Code", value:50},
{name: "Number of Parameters", value:5},
{name: "Number of Methods Called", value:5},
{name: "Number of Accessed Fields", value:5}];

//use class metrics by default;
var metricSpec 	= classMetricSpec;
var metricValues = classMetricValues;
var classMetricThresholds = [];classMetricThresholds["Coupling Between Object Classes"]=[0, 5, 10, 20, 30, 50];
classMetricThresholds["Access to Foreign Data"]=[0, 1, 3, 5, 10, 20];
classMetricThresholds["Number of Fields"]=[0, 20, 30, 40, 50, 60];
classMetricThresholds["Class-Methods Lines of Code"]=[0, 50, 300, 900, 1500, 2000];
classMetricThresholds["Number of Children"]=[0, 1, 5, 10, 15, 20];
classMetricThresholds["Depth of Inheritance Tree"]=[0, 1, 3, 10, 20, 30];
classMetricThresholds["Number of Methods"]=[0, 20, 30, 40, 50, 60];
classMetricThresholds["Response For a Class"]=[0, 50, 100, 150, 200, 250];
classMetricThresholds["Number of Overridden Methods"]=[0, 5, 10, 20, 30, 40];
classMetricThresholds["Class Lines of Code"]=[0, 50, 300, 900, 1500, 2000];
classMetricThresholds["Number of Static Fields"]=[0, 20, 30, 40, 50, 50];
classMetricThresholds["Simple Response For a Class"]=[0, 50, 100, 150, 200, 250];
classMetricThresholds["CBO App"]=[0, 5, 10, 20, 30, 50];
classMetricThresholds["Number of Static Methods"]=[0, 20, 30, 40, 50, 50];
classMetricThresholds["Weighted Method Count"]=[0, 20, 50, 101, 120, 150];
classMetricThresholds["CBO Lib"]=[0, 5, 10, 20, 30, 50];
classMetricThresholds["Lack of Tight Class Cohesion"]=[0.0, 0.6, 0.7, 0.8, 0.9, 1.0];
classMetricThresholds["Lack of Cohesion of Methods"]=[0.0, 0.5, 0.7, 0.8, 1.0, 2.0];
classMetricThresholds["Lack of Cohesion Among Methods(1-CAM)"]=[0.0, 0.6, 0.7, 0.8, 0.9, 1.0];
classMetricThresholds["Specialization Index"]=[0.0, 0.1, 0.3, 0.4, 0.7, 1.2];
var methodMetricThresholds = [];methodMetricThresholds["McCabe Cyclomatic Complexity"]=[0, 10, 17, 20, 25, 30];
methodMetricThresholds["Nested Block Depth"]=[0, 1, 2, 4, 5, 10];
methodMetricThresholds["Number of Methods Called"]=[0, 3, 5, 6, 8, 10];
methodMetricThresholds["Number of Parameters"]=[0, 3, 5, 6, 8, 10];
methodMetricThresholds["Number of Accessed Fields"]=[0, 3, 5, 6, 8, 10];
methodMetricThresholds["Method Lines of Code"]=[0, 30, 50, 100, 200, 300];
var packageMetricThresholds = [];packageMetricThresholds["Number of Entities"]=[0, 5, 10, 30, 40, 50];
packageMetricThresholds["Class Lines of Code"]=[0, 250, 1500, 4500, 7500, 10000];
packageMetricThresholds["Efferent Coupling"]=[0, 5, 10, 20, 100, 150];
packageMetricThresholds["Number of Interfaces"]=[0, 5, 10, 20, 30, 50];
packageMetricThresholds["Number of Classes"]=[0, 5, 10, 20, 30, 50];
packageMetricThresholds["Afferent Coupling"]=[0, 5, 10, 20, 100, 150];
packageMetricThresholds["Weighted Method Count"]=[0, 200, 500, 1000, 2000, 2000];
packageMetricThresholds["Normalized Distance"]=[0.0, 0.1, 0.2, 0.3, 0.4, 1.0];
packageMetricThresholds["Abstractness"]=[0.0, 0.1, 0.2, 0.3, 0.4, 1.0];
packageMetricThresholds["Instability"]=[0.0, 0.1, 0.2, 0.3, 0.4, 1.0];
var projectMetricThresholds = [];projectMetricThresholds["Number of Highly Problematic Classes"]=[0, 10, 100, 200, 300, 400];
projectMetricThresholds["Number of Entities"]=[0, 100, 200, 300, 400, 500];
projectMetricThresholds["Number of Problematic Classes"]=[0, 10, 100, 200, 300, 400];
projectMetricThresholds["Class Lines of Code"]=[0, 1000, 10000, 100000, 1000000, 1100000];
projectMetricThresholds["Number of External Packages"]=[0, 10, 20, 30, 40, 50];
projectMetricThresholds["Number of Packages"]=[0, 10, 20, 30, 40, 50];
projectMetricThresholds["Number of External Entities"]=[0, 100, 200, 300, 400, 500];
