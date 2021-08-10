package com.teamdui.profiler.ui.goaltracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.teamdui.profiler.R;
import com.teamdui.profiler.databinding.FragmentGoaltrackerBinding;
import com.teamdui.profiler.ui.dailycalorie.DailyCalorieFragment;
import com.teamdui.profiler.ui.dailycalorie.DailyExerciseFragment;
import com.teamdui.profiler.ui.dailycalorie.DailyMealFragment;

public class GoaltrackerFragment extends Fragment {

    private GoaltrackerViewModel goaltrackerViewModel;
    private FragmentGoaltrackerBinding binding;
    public Button goalButton;
    public GoalsaveFragment goalsaveFragment = new GoalsaveFragment();

    public static int calorieGoal = 0;
    public static int waterGoal = 0;
    public static  int exerciseGoal = 0;
    public static boolean isGoslsaveVisited = false;

    public static int calorieDaily = 0;
    public static int glassDaily = 0;
    public static int exerciseDaily = 0;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        goaltrackerViewModel =
                new ViewModelProvider(this).get(GoaltrackerViewModel.class);

        binding = FragmentGoaltrackerBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        setGoalVariables();
        setGoalText();

        goalButton = binding.setGoalButton;
        goalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle arg = new Bundle();
                isGoslsaveVisited = true;
                Navigation.findNavController(root).navigate(R.id.action_navigation_goaltracker_to_navigation_goalsave, arg);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public void setGoalVariables()
    {
        if(isGoslsaveVisited)
        {
            calorieGoal = goalsaveFragment.getCalorieGoal();
            waterGoal = goalsaveFragment.getGlassGoal();
            exerciseGoal = goalsaveFragment.getExerciseGoal();
        }
    }
    public void setGoalText()
    {
       try {

            DailyMealFragment dailyMealFragment;
            DailyCalorieFragment dailyCalorieFragment;
            DailyExerciseFragment dailyExerciseFragment;
            dailyCalorieFragment = new DailyCalorieFragment();
            dailyExerciseFragment = new DailyExerciseFragment();
            dailyMealFragment = new DailyMealFragment();
            calorieDaily = dailyMealFragment.getCalorieDaily();
            glassDaily = dailyCalorieFragment.getGlassDaily();
            exerciseDaily = dailyExerciseFragment.getExerciseDaily();
        }
        catch (Exception e)
        {
            calorieDaily = 0;
            glassDaily = 0;
            exerciseDaily = 0;
        }
        String calorieText = binding.calorieEarninTracker.getText().toString();
        binding.calorieEarninTracker.setText(calorieText + String.valueOf(calorieDaily) + "/" + String.valueOf(calorieGoal));
        String waterText = binding.waterTakeninTracker.getText().toString();
        binding.waterTakeninTracker.setText(waterText + String.valueOf(glassDaily) + "/" + String.valueOf(waterGoal));
        String exerciseText = binding.exerciseDoneinTracker.getText().toString();
        binding.exerciseDoneinTracker.setText(exerciseText + String.valueOf(exerciseDaily) + "/" + String.valueOf(exerciseGoal));
    }

}
