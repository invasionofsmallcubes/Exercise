package com.bravofly.dixieland;

import com.bravofly.dixieland.logic.DepthFirstSearch;
import com.bravofly.dixieland.logic.DijkstraAlgorithm;
import com.bravofly.dixieland.logic.Edge;
import com.bravofly.dixieland.logic.Graph;
import com.bravofly.dixieland.logic.visitor.hops.ExactHopsVisitor;
import com.bravofly.dixieland.logic.visitor.hops.MaxHopsVisitor;
import com.bravofly.dixieland.logic.visitor.time.ExactTravelTimeVisitor;

import java.util.List;

public class Routes extends Graph<Airports>
{

  private DepthFirstSearch<Airports> strategy = new DepthFirstSearch<>();

  public int getTravelTime(List<Airports> itinerary)
  {
    int sum = 0;
    for (int i = 0 ; i < itinerary.size() - 1 ; i++)
    {
      Edge<Airports> edgeToSearch = new Edge<>(itinerary.get(i), itinerary.get(i + 1));
      if (!edges.contains(edgeToSearch))
      {
        return -1;
      }
      sum += recoverTravelTime(edgeToSearch);
    }
    return sum;
  }

  public int getRoutesWithExactTravelTime(int timeInterval, Airports source, Airports target)
  {
    return strategy.apply(this, 0, source, new ExactTravelTimeVisitor(timeInterval, target, 0));
  }

  public int getRoutesWithAtMostHops(int maxHops, Airports source, Airports destination)
  {
    return strategy.apply(this, 0, source, new MaxHopsVisitor(maxHops, destination, 0));
  }

  public int getRoutesWithExactHops(int maxHops, Airports source, Airports destination)
  {
    return strategy.apply(this, 0, source, new ExactHopsVisitor(maxHops, destination, 0));
  }

  public int getFastestTravelTime(Airports departure, Airports arrival)
  {
    DijkstraAlgorithm<Airports> d = new DijkstraAlgorithm<>(this);
    if (departure.equals(arrival))
    {
      return getMinPathTravelTime(d.getShortestPathForSameArrival(departure));
    }
    return getTravelTime(d.getShortestPathForDifferentArrival(departure, arrival));
  }

  private int recoverTravelTime(Edge<Airports> searchedEdge)
  {
    for (Edge<Airports> f : edges)
    {
      if (f.equals(searchedEdge))
      {
        return f.getWeight();
      }
    }
    return 0;
  }

  private int getMinPathTravelTime(List<List<Airports>> paths)
  {
    int min = Integer.MAX_VALUE;
    for (List<Airports> path : paths)
    {
      int t = getTravelTime(path);
      if (t < min)
      {
        min = t;
      }
    }
    return min;
  }


}