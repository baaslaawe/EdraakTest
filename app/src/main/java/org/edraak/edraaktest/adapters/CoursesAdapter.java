package org.edraak.edraaktest.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import org.edraak.edraaktest.R;
import org.edraak.edraaktest.models.thin.CourseModel;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Courses adapter
 */
public class CoursesAdapter extends BaseListAdapter
        <CoursesAdapter.CourseViewHolder, CourseModel> {

    private Context context;

    private int lastPosition = -1;

    /**
     * Create an instance
     *
     * @param context a context of the application package implementing
     *                this class.
     */
    public CoursesAdapter(Context context) {
        super();

        this.context = context;
    }

    @Override
    protected CourseViewHolder onCreateRegularViewHolder
            (ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_course, parent, false);

        return new CourseViewHolder(view);
    }

    @Override
    protected void onRegularBindViewHolder
            (CourseViewHolder holder, int position, CourseModel item) {
        setValueNumberWithLabel(holder.getId(), item.getId(), R.string.id);
        setValueNumberWithLabel(holder.getCourseId(),
                item.getCourseId(), R.string.course_id);
        setValueNumberWithLabel(holder.getCourseCategoryId(),
                item.getCourseCategoryId(), R.string.course_category_id);

        animateView(holder.getContainer(), position);
    }

    private void setValueNumberWithLabel
            (TextView textView, long value, int labelResourceId) {
        String label = context.getString(labelResourceId);
        String fullText = String.format(Locale.getDefault(),
                "%s %d", label, value);

        textView.setText(fullText);
    }

    private void animateView(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition) {
            ScaleAnimation anim =
                    new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f,
                            Animation.RELATIVE_TO_SELF, 0.5f,
                            Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random()
                    .nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);

            lastPosition = position;
        }
    }

    @Override
    protected void initItems() {
        setList(new ArrayList<CourseModel>());
    }

    class CourseViewHolder extends
            BaseListAdapter.ItemBaseViewHolder {

        private CardView container;
        private TextView id;
        private TextView courseId;
        private TextView courseCategoryId;

        CourseViewHolder(View itemView) {
            super(itemView);

            container = (CardView) itemView.findViewById(R.id.container);
            id = (TextView) itemView.findViewById(R.id.id);
            courseId = (TextView) itemView.findViewById(R.id.courseId);
            courseCategoryId = (TextView) itemView.findViewById(R.id.courseCategoryId);
        }

        CardView getContainer() {
            return container;
        }

        TextView getId() {
            return id;
        }

        TextView getCourseId() {
            return courseId;
        }

        TextView getCourseCategoryId() {
            return courseCategoryId;
        }
    }
}
