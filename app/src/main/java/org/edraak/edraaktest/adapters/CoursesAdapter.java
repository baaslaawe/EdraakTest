package org.edraak.edraaktest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.edraak.edraaktest.R;
import org.edraak.edraaktest.models.thin.CourseModel;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Courses adapter
 */
public class CoursesAdapter extends BaseListAdapter
        <CoursesAdapter.CourseViewHolder, CourseModel> {

    private Context context;

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
    }

    private void setValueNumberWithLabel
            (TextView textView, long value, int labelResourceId) {
        String label = context.getString(labelResourceId);
        String fullText = String.format(Locale.getDefault(),
                "%s %d", label, value);

        textView.setText(fullText);
    }

    @Override
    protected void initItems() {
        setList(new ArrayList<CourseModel>());
    }

    protected class CourseViewHolder extends
            BaseListAdapter.ItemBaseViewHolder {

        private TextView id;
        private TextView courseId;
        private TextView courseCategoryId;

        public CourseViewHolder(View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.id);
            courseId = (TextView) itemView.findViewById(R.id.courseId);
            courseCategoryId = (TextView) itemView.findViewById(R.id.courseCategoryId);
        }

        public TextView getId() {
            return id;
        }

        public TextView getCourseId() {
            return courseId;
        }

        public TextView getCourseCategoryId() {
            return courseCategoryId;
        }
    }
}
