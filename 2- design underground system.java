class UndergroundSystem {
    private Map<Integer, Object[]> checkInMap;
    private Map<String, double[]> travelStats;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        travelStats = new HashMap<>();
    }
    
    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new Object[]{stationName, t});
    }
    
    public void checkOut(int id, String stationName, int t) {
        Object[] info = checkInMap.get(id);
        String startStation = (String) info[0];
        int startTime = (int) info[1];
        
        String key = startStation + "->" + stationName;
        travelStats.putIfAbsent(key, new double[2]);
        
        double[] stats = travelStats.get(key);
        stats[0] += (t - startTime);
        stats[1] += 1;
        
        checkInMap.remove(id);
    }
    
    public double getAverageTime(String startStation, String endStation) {
        double[] stats = travelStats.get(startStation + "->" + endStation);
        return stats[0] / stats[1];
    }
}

/**
 * Your UndergroundSystem object will be instantiated and called as such:
 * UndergroundSystem obj = new UndergroundSystem();
 * obj.checkIn(id,stationName,t);
 * obj.checkOut(id,stationName,t);
 * double param_3 = obj.getAverageTime(startStation,endStation);
 */
